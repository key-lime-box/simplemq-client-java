
/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/

package org.keylimebox.simplemq.client;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/


/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
@SuppressWarnings ("nls")
public class SimpleMqClientTest
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

    /*==================================================================================*/
    /* Abstract Operations (definitions)                                                */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Abstract Operations (implementations)                                            */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Class (static) Operations                                                        */
    /*==================================================================================*/

   public static void main (String[] args)
   {
      SimpleMqClientConfig myConfig    = new SimpleMqClientConfig ();
      myConfig.setPublisherId          ("54a44fba68376ebe87d3e709");
      myConfig.setSubscriberId         ("54a44fab68376ebe87d3e708");
      myConfig.setUrl                  ("http://10.60.146.4:8080/simplemq");

      SimpleMqClient       myClient    = new SimpleMqClient ();
      myClient.setConfig               (myConfig);

      myClient.publish ("54a44f9c68376ebe87d3e707", DummyObject.create ());
      myClient.publish ("54a44f9c68376ebe87d3e707", DummyObject.create ());
      myClient.publish ("54a44f9c68376ebe87d3e707", DummyObject.create ());
      myClient.publish ("54a44f9c68376ebe87d3e707", DummyObject.create ());
      myClient.publish ("54a44f9c68376ebe87d3e707", DummyObject.create ());
      myClient.publish ("54a44f9c68376ebe87d3e707", DummyObject.create ());

      Iterator<QueueEntry> myIterator = myClient.queueIterator ("54a44f9c68376ebe87d3e707");
      while (myIterator.hasNext ()) {
         QueueEntry myMessage = myIterator.next ();
//         System.out.println (myMessage.getPayload ().toString ());
         try {
            Object myPayload = myMessage.getPayload (DummyObject.class);
            System.out.println (myPayload.toString ());
         } catch (JsonParseException myException) {
            myException.printStackTrace();
         } catch (JsonMappingException myException) {
            myException.printStackTrace();
         } catch (IOException myException) {
            myException.printStackTrace();
         }

      }

   }

}

// EOF  SimpleMqClientTest.java

