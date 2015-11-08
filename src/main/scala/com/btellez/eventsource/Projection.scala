package com.btellez.eventsource

/**
 * Projection represents a type of computation that is done with an event.
 *
 * Each event has an effect on the system being represented.
 *
 * For example:
 *   Consider a Financial Ledger.
 *   Given an event such as a new expense takes place.
 *   Then we must update the state(or projection) to reflect a new total.
 */
trait Projection[T <: Event] {
  /**
   * Project accepts an event and has a side-effect that updates an underlying system.
   *
   * @param event - event to use to alter current state.
   */
  def project(event: T): Unit
}
