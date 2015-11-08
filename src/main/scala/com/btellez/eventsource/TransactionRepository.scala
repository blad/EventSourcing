package com.btellez.eventsource

/**
 * Transaction Repository takes an event an then commits it into a place for permanent storage.
 */
trait TransactionRepository[T <: Event] {
  /**
   * Commit the Transaction to persistent storage.
   *
   * @param event - the event that should be persisted to storage.
   * @return the updated event object with a flag to indicate it has been committed to the ledger.
   */
  def commit(event: T)(success: T => Unit, failure: T => Unit): Unit
}
