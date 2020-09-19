fun main() {

    val transferAmount: Int = 100_000_000
    var lastPurchases: Int = 0
    calculatePrice(transferAmount)

    lastPurchases += transferAmount

}

fun calculatePrice(
    transferAmount: Int,
    cardType: String = "vkPay",
    lastPurchases: Int = 0,
): Int {

    return if (checkLimits(transferAmount, lastPurchases, cardType)) {
        when (cardType) {
            "vkPay" -> return 0
            "Mastercard" -> return firstTypeOfCards(transferAmount)
            "Maestro" -> return firstTypeOfCards(transferAmount)
            "Visa" -> return secondTypeOfCards(transferAmount)
            "Мир" -> return secondTypeOfCards(transferAmount)
            else -> return 999999999
        }
    } else {
        println("Превышины лимиты карты")
        return 999999999
    }
}


// Mastercard and Maestro
fun firstTypeOfCards(transferAmount: Int): Int {
    return when (transferAmount) {
        in 0..75_000 -> 0
        else -> (transferAmount * 0.006 + 20).toInt()
    }
}

// Visa and Мир
fun secondTypeOfCards(transferAmount: Int): Int {
    return if (transferAmount * 0.0075 < 35_000) {
        35_000
    } else {
        (transferAmount * 0.0075).toInt()
    }
}

fun checkLimits(transferAmount: Int, lastPurchases: Int, cardType: String): Boolean {
    return if (cardType == "vkPay") {
        transferAmount < 15_000 || lastPurchases < 40_000
    } else {
        transferAmount < 150_000_000 || lastPurchases < 600_000_000
    }
}


