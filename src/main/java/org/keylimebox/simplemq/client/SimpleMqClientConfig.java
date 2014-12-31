
/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.simplemq.client;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       SimpleMqClientConfig                                                    */
/**
 * Auto-configuration for the Simple MQ Client. The configuration are loaded by Spring.
 * The expected configuration properties are:
 * <ul>
 *    <li><code>keylimebox.simplemq.url</code>: The URL to the Simple MQ server (e.g. <code>http://simplemq.keylimebox.org</code>).</li>
 *    <li><code>keylimebox.simplemq.subscriberId</code>: The Subscriber ID for this application (if it is going to read from queues).</li>
 *    <li><code>keylimebox.simplemq.publisherId</code>: The Publisher ID for this application (if it is going to publish to queues).</li>
 * </ul>
 * <p>
 * @author      etlweather
 * @since       Dec 31, 2014
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
@Component
public class SimpleMqClientConfig
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
                /* ATTRIBUTE: url                                                       */
                /**
                 * The URL to the Simple MQ server (e.g. <code>http://simplemq.keylimebox.org</code>).
                 */
                /*======================================================================*/
   @Value ("${keylimebox.simplemq.url}")
   private String                   url;

                /*======================================================================*/
                /* ATTRIBUTE: subscriberId                                              */
                /**
                 * The Subscriber ID for this application (if it is going to read from queues).
                 */
                /*======================================================================*/
   @Value ("${keylimebox.simplemq.subscriberId}")
   private String                   subscriberId;

                /*======================================================================*/
                /* ATTRIBUTE: publisherId                                               */
                /**
                 * The Publisher ID for this application (if it is going to publish to queues).
                 */
                /*======================================================================*/
   @Value ("${keylimebox.simplemq.publisherId}")
   private String                   publisherId;

    /*==================================================================================*/
    /* Class Attributes                                                                 */
    /*==================================================================================*/
        /*==============================================================================*/
        /* Constants                                                                    */
        /*==============================================================================*/

                /*======================================================================*/
                /* ATTRIBUTE: URL_PUBLISH                                               */
                /**
                 * The publish URL suffix.
                 */
                /*======================================================================*/
   private static final String      URL_PUBLISH = "/api/queues/{queueId}/publish?publisher={publisherId}";

                /*======================================================================*/
                /* ATTRIBUTE: URL_NEXT                                                  */
                /**
                 * The next message URL suffix.
                 */
                /*======================================================================*/
   private static final String      URL_NEXT    = "/api/queues/{queueId}/next?subscriber={subscriberId}&previous={previousId}";

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

   public String getUrl ()
   {
      return (url);
   }

   public String getSubscriberId ()
   {
      return (subscriberId);
   }

   public String getPublisherId ()
   {
      return (publisherId);
   }

         /*=============================================================================*/
         /* OPERATION:   getNextUrl                                                     */
         /**
          * Returns the URL template to get the next message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public String getNextUrl ()
   {
      return url + URL_NEXT;
   }

         /*=============================================================================*/
         /* OPERATION:   getPublishUrl                                                  */
         /**
          * Returns the URL template to publish a message.
          * <p>
          * @since Dec 31, 2014
          */
         /*=============================================================================*/
   public String getPublishUrl ()
   {
      return url + URL_PUBLISH;
   }

    /*==================================================================================*/
    /* Attribute Set Operations                                                         */
    /*==================================================================================*/

   public void setPublisherId (String aPublisherId)
   {
      publisherId = aPublisherId;
   }

   public void setSubscriberId (String aSubscriberId)
   {
      subscriberId = aSubscriberId;
   }

   public void setUrl (String aUrl)
   {
      url = aUrl;
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

// EOF  SimpleMqClientConfig.java
