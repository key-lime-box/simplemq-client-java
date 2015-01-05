
/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.simplemq.client;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       SimpleMqClient                                                          */
/**
 * A client to the Key Lime Box's Simple MQ server. The Simple MQ Client is a Spring
 * service. It is configured automatically through <code>SimpleMqClientConfig</code>.
 * <p>
 * @author      etlweather
 * @since       Dec 31, 2014
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
@Service
public class SimpleMqClient
{

    /*==================================================================================*/
    /*===================================            ===================================*/
    /*=================================== Attributes ===================================*/
    /*===================================            ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Protected Attributes                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Private Attributes                                                               */
    /*==================================================================================*/

                /*======================================================================*/
                /* ATTRIBUTE: restTemplate                                              */
                /**
                 * The rest remplate used to interact with the server.
                 */
                /*======================================================================*/
   private RestTemplate                rest = new RestTemplate ();

                /*======================================================================*/
                /* ATTRIBUTE: config                                                    */
                /**
                 * The configuration.
                 */
                /*======================================================================*/
   @Autowired
   private SimpleMqClientConfig        config;


                /*======================================================================*/
                /* ATTRIBUTE: sequentialExecutor                                        */
                /**
                 * Executor that executes in sequence the tasks given it.
                 */
                /*======================================================================*/
   private ExecutorService             sequentialExecutor;

                /*======================================================================*/
                /* ATTRIBUTE: multiThreadExecutor                                       */
                /**
                 * Executor that users multi-threads (5) and does not guarantee the sequence.
                 */
                /*======================================================================*/
   private ExecutorService             multiThreadExecutor;

    /*==================================================================================*/
    /* Class Attributes                                                                 */
    /*==================================================================================*/
        /*==============================================================================*/
        /* Constants                                                                    */
        /*==============================================================================*/

        /*==============================================================================*/
        /* Variables                                                                    */
        /*==============================================================================*/

    /*==================================================================================*/
    /*===================================            ===================================*/
    /*=================================== Operations ===================================*/
    /*===================================            ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Static initializer                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Constructors                                                                     */
    /*==================================================================================*/

        /*==============================================================================*/
        /* OPERATION:   SimpleMqClient                                                  */
        /**
         * The constructor for this class.
         * <p>
         * @since Jan 4, 2015
         */
        /*==============================================================================*/
   public SimpleMqClient ()
   {
      sequentialExecutor   = Executors.newSingleThreadExecutor ();
      multiThreadExecutor  = Executors.newFixedThreadPool (5);
   }

    /*==================================================================================*/
    /* Attribute Get Operations                                                         */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/

   void setConfig (SimpleMqClientConfig aConfig)
   {
      config = aConfig;
   }

    /*==================================================================================*/
    /* Private Operations                                                               */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   createPublishTask                                              */
         /**
          * Creates a runnable task that publishes the given payload to the given queue.
          * <p>
          * @param aQueueId
          * @param aPayload
          * @return
          * <p>
          * @since Jan 4, 2015
          */
         /*=============================================================================*/
   private Runnable createPublishTask (final String aQueueId, final Object aPayload)
   {
      final Map<String, String> myParams = new HashMap<String, String> ();
      myParams.put ("publisherId", config.getPublisherId ());
      myParams.put ("queueId", aQueueId);
      return new Runnable()
      {
         @Override
         public void run ()
         {
            for (int i = 1; i <= 100; i ++) {
               try {
                  ResponseEntity<String> myResponse = rest.postForEntity (config.getPublishUrl (), aPayload, String.class, myParams);
                  System.out.println ("Published " + aPayload.toString ());
                  if (myResponse.getStatusCode ().is2xxSuccessful ()) {
                     return;
                  }
                  else {
                     throw new IllegalStateException (myResponse.getStatusCode ().getReasonPhrase ());
                  }
               }
               catch (ResourceAccessException myRAException) {
                  try {
                     System.out.println ("sleeping " + (200 * i));
                     Thread.sleep (200 * i);
                  }
                  catch (InterruptedException myException) {
                     return;
                  }
               }
            }
         }
      };

   }

    /*==================================================================================*/
    /* Protected Operations                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Package Operations                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Public Operations                                                                */
    /*==================================================================================*/

         /*=============================================================================*/
         /* OPERATION:   publish                                                        */
         /**
          * Publish a message to a queue - messages are not guaranteed to be published
          * in the queue in the sequence they were submitted.
          * <p>
          * @param aQueueId
          *          The ID of the queue to publish to.
          *
          * @param aPayload
          *          The payload to put into the message.
          *
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void publish (String aQueueId, Object aPayload)
   {
      multiThreadExecutor.execute (createPublishTask (aQueueId, aPayload));
   }

         /*=============================================================================*/
         /* OPERATION:   publishInSequence                                              */
         /**
          * Publishes a message to a queue. Messages are going to be published in the
          * same sequence.
          * <p>
          * @param aQueueId
          * @param aPayload
          * <p>
          * @since Jan 4, 2015
          */
         /*=============================================================================*/
   public void publishInSequence (String aQueueId, Object aPayload)
   {
      sequentialExecutor.execute (createPublishTask (aQueueId, aPayload));
   }



         /*=============================================================================*/
         /* OPERATION:   next                                                           */
         /**
          * Calls the next method on the queue to get the next message and optionally
          * delete first the one that was just handled.
          * <p>
          * @param aQueueId
          * @param aPreviousId
          * @return
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public QueueEntry next (String aQueueId, String aPreviousId)
   {

      Map<String, String> myParams = new HashMap<String, String> ();

      myParams.put ("subscriberId", config.getSubscriberId ());
      myParams.put ("queueId",      aQueueId);
      myParams.put ("previousId",   aPreviousId);


      return rest.getForObject (config.getNextUrl (), QueueEntry.class, myParams);
   }

         /*=============================================================================*/
         /* OPERATION:   queueIterator                                                  */
         /**
          * Gets an iterator for processing a given queue.
          * <p>
          * @param aQueueId
          *          The ID of the queue to process.
          *
          * @return The iterator.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public Iterator<QueueEntry> queueIterator (String aQueueId)
   {
      return new QueueIterator (aQueueId, this);
   }


         /*=============================================================================*/
         /* OPERATION:   multiQueueIterator                                            */
         /**
          * Gets an iterator for pocessing multiple queues as one.
          * <p>
          * @param aQueueIds
          *          The ID of the queues to be processed.
          *
          * @return the iterator.
          * <p>
          * @since Jan 2, 2015
          */
         /*=============================================================================*/
   public Iterator<QueueEntry> queueIterator (String ... aQueueIds)
   {
      return new MultiQueueIterator (aQueueIds, this);
   }



    /*==================================================================================*/
    /* Abstract Operations (definitions)                                                */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Abstract Operations (implementations)                                            */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Class (static) Operations                                                        */
    /*==================================================================================*/
}

// EOF  SimpleMqClient.java
