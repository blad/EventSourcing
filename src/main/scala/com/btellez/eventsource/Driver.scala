package com.btellez.eventsource

object Driver {

  /**
   * Sample Repository implementation
   */
  class Repository extends TransactionRepository[Event] {
    /**
     * Commit the Transaction to persistent storage.
     *
     * @param event - the event that should be persisted to storage.
     * @return the updated event object with a flag to indicate it has been committed to the ledger.
     */
    override def commit(event: Event)(success: (Event) => Unit, failure: (Event) => Unit): Unit = {
      System.out.println("Committing Event to Log")
      System.out.println(event.payload, event.committed)
      // Return Event with committed set to true if Committed Successfully
      if (true)
        success(new CreateUserEvent(event.payload, true))
      else
        failure(event)
    }
  }

  /**
   * Sample Projection implementation.
   */
  class SampleProjection extends Projection[Event] {
    /**
     * Project accepts a function that accepts an event that is then
     * used to compute new current state for that event.
     *
     * @param event - event to use to alter current state based on the type of event.
     */
    override def project(event: Event): Unit = {
      System.out.println("Updating Projection based on Event...")
      System.out.println(event.payload, event.committed)
    }
  }

  /**
   * Create User Event
   *
   * @param eventPayload
   * @param isCommited
   */
  class CreateUserEvent(val eventPayload: String, val isCommited: Boolean = false) extends Event {
    override def committed: Boolean = isCommited
    override def payload: String = eventPayload
  }

  /**
   * Program Entry Point
   *
   * @param args
   */
  def main(args: Array[String]) {
    new Repository().commit(new CreateUserEvent("Create a new User Event...."))(
      { event =>
        new SampleProjection().project(event)
      }, { event =>
        // TODO: Handle Error Case
      }
      )
  }
}
