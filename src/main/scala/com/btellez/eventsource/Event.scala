package com.btellez.eventsource

/**
 * Event for the system to consume.
 */
trait Event {
  def payload: String
  def committed: Boolean
}
