# Key Lime Box's Simple MQ - Java Client

This is a Java client library to interact with a *Key Lime Box's Simple MQ* server.

# Spring Ready

The Simple MQ Java Client is built with Spring in mind.

# How to Use

First, you must set up the required configuration properties:

 - `keylimebox.simplemq.url`: The URL to the Simple MQ server (e.g. `http://simplemq.keylimebox.org`).
 - `keylimebox.simplemq.subscriberId`: The Subscriber ID for this application (if it is going to read from queues).
 - `keylimebox.simplemq.publisherId`: The Publisher ID for this application (if it is going to publish to queues).

Then you must make sure that Spring will scan the ``org.keylimebox.simplemq.client` package for components. E.g.: 


Then, autowire the `SimpleMqClient`.

## Publishing a Message

Here is an example on how to publish a message to a queue:

```
simpleMqClient.publish (myQueueId, myPayload);
```

Alternatively, the `publishInSequence` method can be used to ensure that the messages
are going to be saved in the queue in the sequence they are published.



## Consuming a Queue

The API for consuming queues is based on the `Iterator` (factually, the `Simple MQ` 
client gives you an `Iterator` instance to work with).

To consume one or more queue:

```
Iterator<QueueEntry> myIterator = simpleMqClient.queueIterator ("54a44fba68376ebe87d3e709");
while (myIterator.hasNext ()) {
   QueueEntry myEntry = myIterator.next ();
   SomeModel myInstance = (SomeModel) myEntry.getPayload (SomeModel.class);
   // do something with the deserialized payload
}
```

### Consuming Multiple Queues as One

There may be scenarios where you want to consume the messages of multiple queues in one
iteration. This can be done by passing more than one Queue ID to the `queueIterator` 
method of `SimpleMqClient`.



 