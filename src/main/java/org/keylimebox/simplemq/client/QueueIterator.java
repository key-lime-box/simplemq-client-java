
/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.simplemq.client;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

import java.util.Iterator;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
@SuppressWarnings ("nls")
public class QueueIterator implements Iterator<QueueEntry>
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
                /* ATTRIBUTE: queueId                                                   */
                /**
                 * The ID of the queue being iterated.
                 */
                /*======================================================================*/
   private String                queueId;

                /*======================================================================*/
                /* ATTRIBUTE: current                                                   */
                /**
                 * The current message.
                 */
                /*======================================================================*/
   private QueueEntry            current = null;

                /*======================================================================*/
                /* ATTRIBUTE: client                                                    */
                /**
                 * The client to get data from.
                 */
                /*======================================================================*/
   private SimpleMqClient        client;


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
        /* OPERATION:   QueueIterator                                                   */
        /**
         * The constructor for this class.
         *
         * <p>
         * @param aQueueId
         *           The ID of the queue this iterator is for.
         * <p>
         * @since Dec 31, 2014
         */
        /*==============================================================================*/
   QueueIterator (String aQueueId, SimpleMqClient aClient)
   {
      queueId  = aQueueId;
      client   = aClient;
   }

    /*==================================================================================*/
    /* Attribute Get Operations                                                         */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/

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

         /*=============================================================================*/
         /* OPERATION:   hasNext                                                        */
         /**
          * Checks if there are more items in the queue and collects the next item ready
          * for processing. It also deletes the previous item from the queue.
          *
          * @return true if there are more items to be processed.
          * <p>
          * @see java.util.Iterator#hasNext()
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   @Override
   public boolean hasNext ()
   {
      current = client.next (queueId, ((current != null) ? current.getId () : null));
      return current != null && current.getId () != null;
   }

         /*=============================================================================*/
         /* OPERATION:   next                                                           */
         /**
          * Returns the next item from the queue.
          *
          * @return the item.
          * <p>
          * @see java.util.Iterator#next()
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   @Override
   public QueueEntry next ()
   {
      return current;
   }

         /*=============================================================================*/
         /* OPERATION:   remove                                                         */
         /**
          * Not supported.
          * <p>
          * @see java.util.Iterator#remove()
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   @Override
   public void remove ()
   {
      throw new UnsupportedOperationException ("Not supported");
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

// EOF  QueueIterator.java
