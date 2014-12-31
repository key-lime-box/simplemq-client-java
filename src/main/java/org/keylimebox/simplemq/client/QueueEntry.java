
/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.simplemq.client;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       Message                                                                 */
/**
 * A message from the message queue.
 * <p>
 * @author      etlweather
 * @since       Dec 31, 2014
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueEntry
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
                /* ATTRIBUTE: id                                                        */
                /**
                 * The ID of the message.
                 */
                /*======================================================================*/
   private String             id;


                /*======================================================================*/
                /* ATTRIBUTE: queue                                                     */
                /**
                 * The ID of the queue the message is for.
                 */
                /*======================================================================*/
   private String             queueId;

                /*======================================================================*/
                /* ATTRIBUTE: subscribedId                                              */
                /**
                 * The ID of the subscriber this queued message is for.
                 */
                /*======================================================================*/
   private String             subscriberId;

                /*======================================================================*/
                /* ATTRIBUTE: publisherId                                               */
                /**
                 * The ID of the publisher.
                 */
                /*======================================================================*/
   private String             publisherId;

                /*======================================================================*/
                /* ATTRIBUTE: dateQueued                                                */
                /**
                 * The date/time the message was queued.
                 */
                /*======================================================================*/
   private Date               dateQueued;

                /*======================================================================*/
                /* ATTRIBUTE: payload                                                   */
                /**
                 * The message's payload.
                 */
                /*======================================================================*/
   private Object             payload;

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

    /*==================================================================================*/
    /* Attribute Get Operations                                                         */
    /*==================================================================================*/


         /*=============================================================================*/
         /* OPERATION:   getId                                                          */
         /**
          * Returns the message's ID.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public String getId ()
   {
      return (id);
   }

         /*=============================================================================*/
         /* OPERATION:   getQueueId                                                     */
         /**
          * Returns the Id of the queue the message was published to.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public String getQueueId ()
   {
      return (queueId);
   }

         /*=============================================================================*/
         /* OPERATION:   getSubscriberId                                            */
         /**
          * Returns the Id of the subscriber this queued message is for.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public String getSubscriberId ()
   {
      return (subscriberId);
   }

         /*=============================================================================*/
         /* OPERATION:   getPublisherId                                            */
         /**
          * Returns the ID of the publisher that published this message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public String getPublisherId ()
   {
      return (publisherId);
   }

         /*=============================================================================*/
         /* OPERATION:   getDateQueued                                            */
         /**
          * Returns the date the message was put into the queue.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public Date getDateQueued ()
   {
      return (dateQueued);
   }

         /*=============================================================================*/
         /* OPERATION:   getPayload                                            */
         /**
          * Returns the payload of the message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public Object getPayload ()
   {
      return (payload);
   }



    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/



         /*=============================================================================*/
         /* OPERATION:   setId                                            */
         /**
          * Sets the ID of the message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void setId (String aId)
   {
      id = aId;
   }

         /*=============================================================================*/
         /* OPERATION:   setQueueId                                            */
         /**
          * Sets the ID of the queue this message is for.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void setQueueId (String aQueueId)
   {
      queueId = aQueueId;
   }

         /*=============================================================================*/
         /* OPERATION:   setSubscriberId                                            */
         /**
          * Sets the ID of the subscriber this queued message is for.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void setSubscriberId (String aSubscriberId)
   {
      subscriberId = aSubscriberId;
   }

         /*=============================================================================*/
         /* OPERATION:   setPublisherId                                            */
         /**
          * Sets the publisher of this message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void setPublisherId (String aPublisherId)
   {
      publisherId = aPublisherId;
   }

         /*=============================================================================*/
         /* OPERATION:   setDateQueued                                            */
         /**
          * Sets the date the message was put into the queue.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void setDateQueued (Date aDateQueued)
   {
      dateQueued = aDateQueued;
   }

         /*=============================================================================*/
         /* OPERATION:   setPayload                                            */
         /**
          * Sets the payload for the message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public void setPayload (Object aPayload)
   {
      payload = aPayload;
   }

    /*==================================================================================*/
    /* Private Operations                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Protected Operations                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Package Operations                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Public Operations                                                                */
    /*==================================================================================*/

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

// EOF  QueueMessage.java