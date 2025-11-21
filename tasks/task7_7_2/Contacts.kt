/**
 * Task 7.7.2 - Contacts Manager
 * 
 * This program manages a collection of contacts using Kotlin collections.
 * It demonstrates the use of sets and maps for storing and retrieving contact information.
 */

/**
 * Data class representing a contact with name and phone number
 */
data class Contact(
    val name: String,
    val phoneNumber: String
) {
    override fun toString(): String = "$name: $phoneNumber"
}

/**
 * ContactsManager class that manages a collection of contacts using Set and Map
 */
class ContactsManager {
    // Using Set to store unique contacts
    private val contactsSet: MutableSet<Contact> = mutableSetOf()
    
    // Using Map for quick phone number lookup by name
    private val contactsMap: MutableMap<String, String> = mutableMapOf()
    
    /**
     * Adds a new contact to both set and map
     */
    fun addContact(name: String, phoneNumber: String): Boolean {
        val contact = Contact(name, phoneNumber)
        
        // Add to set (automatically handles duplicates based on data class equality)
        val addedToSet = contactsSet.add(contact)
        
        // Add to map (will overwrite if name already exists)
        contactsMap[name] = phoneNumber
        
        return addedToSet
    }
    
    /**
     * Removes a contact by name
     */
    fun removeContact(name: String): Boolean {
        val phoneNumber = contactsMap[name]
        if (phoneNumber != null) {
            contactsMap.remove(name)
            contactsSet.remove(Contact(name, phoneNumber))
            return true
        }
        return false
    }
    
    /**
     * Finds a contact by name using Map for O(1) lookup
     */
    fun findContactByName(name: String): Contact? {
        val phoneNumber = contactsMap[name]
        return phoneNumber?.let { Contact(name, it) }
    }
    
    /**
     * Finds contacts by phone number using Set filtering
     */
    fun findContactsByPhoneNumber(phoneNumber: String): Set<Contact> {
        return contactsSet.filter { it.phoneNumber == phoneNumber }.toSet()
    }
    
    /**
     * Gets all contacts sorted by name
     */
    fun getAllContacts(): List<Contact> {
        return contactsSet.sortedBy { it.name }
    }
    
    /**
     * Gets all contact names
     */
    fun getAllContactNames(): Set<String> {
        return contactsMap.keys
    }
    
    /**
     * Gets all phone numbers
     */
    fun getAllPhoneNumbers(): Collection<String> {
        return contactsMap.values
    }
    
    /**
     * Checks if a contact exists by name
     */
    fun containsContact(name: String): Boolean {
        return name in contactsMap
    }
    
    /**
     * Gets the total number of contacts
     */
    fun getContactCount(): Int {
        return contactsSet.size
    }
    
    /**
     * Clears all contacts
     */
    fun clearAllContacts() {
        contactsSet.clear()
        contactsMap.clear()
    }
    
    /**
     * Updates a contact's phone number
     */
    fun updateContactPhoneNumber(name: String, newPhoneNumber: String): Boolean {
        if (name in contactsMap) {
            // Remove old contact from set
            val oldPhoneNumber = contactsMap[name]
            if (oldPhoneNumber != null) {
                contactsSet.remove(Contact(name, oldPhoneNumber))
            }
            
            // Update in map and set
            contactsMap[name] = newPhoneNumber
            contactsSet.add(Contact(name, newPhoneNumber))
            return true
        }
        return false
    }
    
    /**
     * Displays all contacts in a formatted way
     */
    fun displayAllContacts() {
        if (contactsSet.isEmpty()) {
            println("No contacts available.")
            return
        }
        
        println("\n=== All Contacts ===")
        getAllContacts().forEachIndexed { index, contact ->
            println("${index + 1}. $contact")
        }
    }
    
    /**
     * Displays contacts grouped by phone number area code
     */
    fun displayContactsByAreaCode() {
        val contactsByAreaCode = contactsSet.groupBy { contact ->
            contact.phoneNumber.takeWhile { it.isDigit() }.take(3) // Simple area code extraction
        }
        
        println("\n=== Contacts by Area Code ===")
        contactsByAreaCode.forEach { (areaCode, contacts) ->
            println("Area Code $areaCode:")
            contacts.forEach { contact ->
                println("  - $contact")
            }
        }
    }
}

/**
 * Extension functions for additional functionality
 */

/**
 * Extension function to find contacts whose names start with a given prefix
 */
fun ContactsManager.findContactsByNamePrefix(prefix: String): List<Contact> {
    return contactsSet.filter { it.name.startsWith(prefix, ignoreCase = true) }
        .sortedBy { it.name }
}

/**
 * Extension function to get duplicate phone numbers
 */
fun ContactsManager.findDuplicatePhoneNumbers(): Map<String, List<Contact>> {
    return contactsSet.groupBy { it.phoneNumber }
        .filter { it.value.size > 1 }
}

/**
 * Main function that provides an interactive contacts manager
 */
fun main() {
    val contactsManager = ContactsManager()
    
    println("=== Contacts Manager ===")
    println("A simple contacts management system using Kotlin collections")
    
    // Add some sample contacts
    contactsManager.addContact("Alice Johnson", "555-0101")
    contactsManager.addContact("Bob Smith", "555-0102")
    contactsManager.addContact("Carol Davis", "555-0103")
    contactsManager.addContact("David Wilson", "555-0101") // Same number as Alice
    contactsManager.addContact("Eve Brown", "555-0104")
    
    // Demonstrate various operations
    demonstrateContactsOperations(contactsManager)
    
    // Interactive menu (optional)
    runInteractiveMenu(contactsManager)
}

/**
 * Demonstrates various contacts operations
 */
fun demonstrateContactsOperations(contactsManager: ContactsManager) {
    println("\n" + "=".repeat(50))
    println("DEMONSTRATION OF CONTACTS OPERATIONS")
    println("=".repeat(50))
    
    // 1. Display all contacts
    contactsManager.displayAllContacts()
    
    // 2. Demonstrate Set operations
    println("\n--- Set Operations ---")
    println("Total unique contacts: ${contactsManager.getContactCount()}")
    println("Contact names: ${contactsManager.getAllContactNames()}")
    
    // 3. Demonstrate Map operations
    println("\n--- Map Operations ---")
    println("All phone numbers: ${contactsManager.getAllPhoneNumbers()}")
    
    // 4. Search operations
    println("\n--- Search Operations ---")
    val alice = contactsManager.findContactByName("Alice Johnson")
    println("Find Alice Johnson: $alice")
    
    val unknown = contactsManager.findContactByName("Unknown Person")
    println("Find Unknown Person: $unknown")
    
    // 5. Find by phone number
    val contactsWithNumber = contactsManager.findContactsByPhoneNumber("555-0101")
    println("Contacts with phone 555-0101: $contactsWithNumber")
    
    // 6. Extension function demonstrations
    println("\n--- Extension Functions ---")
    val aContacts = contactsManager.findContactsByNamePrefix("A")
    println("Contacts starting with 'A': $aContacts")
    
    val duplicates = contactsManager.findDuplicatePhoneNumbers()
    println("Duplicate phone numbers: $duplicates")
    
    // 7. Update operation
    println("\n--- Update Operation ---")
    contactsManager.updateContactPhoneNumber("Alice Johnson", "555-9999")
    println("After updating Alice's number:")
    contactsManager.displayAllContacts()
    
    // 8. Remove operation
    println("\n--- Remove Operation ---")
    contactsManager.removeContact("Bob Smith")
    println("After removing Bob Smith:")
    contactsManager.displayAllContacts()
    
    // 9. Grouping demonstration
    contactsManager.displayContactsByAreaCode()
}

/**
 * Interactive menu for testing the contacts manager
 */
fun runInteractiveMenu(contactsManager: ContactsManager) {
    println("\n" + "=".repeat(50))
    println("INTERACTIVE MENU")
    println("=".repeat(50))
    
    while (true) {
        println("\nOptions:")
        println("1. Add contact")
        println("2. Find contact by name")
        println("3. Display all contacts")
        println("4. Remove contact")
        println("5. Update phone number")
        println("6. Find by phone number")
        println("7. Show statistics")
        println("8. Exit")
        
        print("Choose an option (1-8): ")
        val choice = readlnOrNull()?.trim()
        
        when (choice) {
            "1" -> addContactInteractive(contactsManager)
            "2" -> findContactInteractive(contactsManager)
            "3" -> contactsManager.displayAllContacts()
            "4" -> removeContactInteractive(contactsManager)
            "5" -> updateContactInteractive(contactsManager)
            "6" -> findByPhoneInteractive(contactsManager)
            "7" -> showStatistics(contactsManager)
            "8" -> {
                println("Goodbye!")
                break
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}

/**
 * Interactive function to add a contact
 */
fun addContactInteractive(manager: ContactsManager) {
    print("Enter name: ")
    val name = readlnOrNull()?.trim() ?: ""
    
    if (name.isEmpty()) {
        println("Name cannot be empty.")
        return
    }
    
    print("Enter phone number: ")
    val phone = readlnOrNull()?.trim() ?: ""
    
    if (phone.isEmpty()) {
        println("Phone number cannot be empty.")
        return
    }
    
    if (manager.addContact(name, phone)) {
        println("Contact '$name' added successfully.")
    } else {
        println("Contact '$name' already exists (updated phone number).")
    }
}

/**
 * Interactive function to find a contact
 */
fun findContactInteractive(manager: ContactsManager) {
    print("Enter name to search: ")
    val name = readlnOrNull()?.trim() ?: ""
    
    if (name.isEmpty()) {
        println("Please enter a name to search.")
        return
    }
    
    val contact = manager.findContactByName(name)
    if (contact != null) {
        println("Found: $contact")
    } else {
        println("Contact '$name' not found.")
    }
}

/**
 * Interactive function to remove a contact
 */
fun removeContactInteractive(manager: ContactsManager) {
    print("Enter name to remove: ")
    val name = readlnOrNull()?.trim() ?: ""
    
    if (name.isEmpty()) {
        println("Please enter a name to remove.")
        return
    }
    
    if (manager.removeContact(name)) {
        println("Contact '$name' removed successfully.")
    } else {
        println("Contact '$name' not found.")
    }
}

/**
 * Interactive function to update a contact
 */
fun updateContactInteractive(manager: ContactsManager) {
    print("Enter name to update: ")
    val name = readlnOrNull()?.trim() ?: ""
    
    if (name.isEmpty()) {
        println("Please enter a name to update.")
        return
    }
    
    print("Enter new phone number: ")
    val phone = readlnOrNull()?.trim() ?: ""
    
    if (phone.isEmpty()) {
        println("Phone number cannot be empty.")
        return
    }
    
    if (manager.updateContactPhoneNumber(name, phone)) {
        println("Contact '$name' updated successfully.")
    } else {
        println("Contact '$name' not found.")
    }
}

/**
 * Interactive function to find by phone number
 */
fun findByPhoneInteractive(manager: ContactsManager) {
    print("Enter phone number to search: ")
    val phone = readlnOrNull()?.trim() ?: ""
    
    if (phone.isEmpty()) {
        println("Please enter a phone number to search.")
        return
    }
    
    val contacts = manager.findContactsByPhoneNumber(phone)
    if (contacts.isNotEmpty()) {
        println("Contacts with phone '$phone':")
        contacts.forEach { println("  - $it") }
    } else {
        println("No contacts found with phone '$phone'.")
    }
}

/**
 * Shows statistics about contacts
 */
fun showStatistics(manager: ContactsManager) {
    println("\n=== Contacts Statistics ===")
    println("Total contacts: ${manager.getContactCount()}")
    println("Contact names: ${manager.getAllContactNames().sorted().joinToString(", ")}")
    println("Unique phone numbers: ${manager.getAllPhoneNumbers().toSet().size}")
    
    val duplicates = manager.findDuplicatePhoneNumbers()
    if (duplicates.isNotEmpty()) {
        println("Duplicate numbers found:")
        duplicates.forEach { (phone, contacts) ->
            println("  $phone: ${contacts.joinToString { it.name }}")
        }
    } else {
        println("No duplicate phone numbers.")
    }
}
