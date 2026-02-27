/**
 * SmartQueue - Project Milestone 1
 * SE 3242: Android Application Development
 *
 * This file defines the core data model for the SmartQueue application.
 * SmartQueue is a queue management system designed to organize clients
 * in service environments using digital queue tickets.
 */
/**
 * Represents a queue ticket in the SmartQueue system.
 *
 * @property ticketId Unique identifier for the ticket (e.g., "T001").
 * @property userName Name of the client in the queue.
 * @property queueNumber Position number assigned to the client.
 * @property isServed Indicates whether the client has been served.
 * @property serviceTime Time in minutes spent serving the client.
 * This property is nullable because a ticket may not yet be served.
 */
data class Ticket(
    val ticketId: String,
    val userName: String,
    val queueNumber: Int,
    val isServed: Boolean,
    val serviceTime: Int?
)

/**
 * Main function to test the Ticket data class.
 * Creates three sample tickets and prints them.
 */
fun main() {

    val ticket1 = Ticket(
        ticketId = "T001",
        userName = "Alice",
        queueNumber = 1,
        isServed = false,
        serviceTime = null
    )

    val ticket2 = Ticket(
        ticketId = "T002",
        userName = "Tracy",
        queueNumber = 2,
        isServed = true,
        serviceTime = 15
    )

    val ticket3 = Ticket(
        ticketId = "T003",
        userName = "Queenie",
        queueNumber = 3,
        isServed = true,
        serviceTime = 10
    )

    println(ticket1)
    println(ticket2)
    println(ticket3)
}