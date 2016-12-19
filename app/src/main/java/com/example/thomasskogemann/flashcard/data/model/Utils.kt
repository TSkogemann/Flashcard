package com.example.thomasskogemann.flashcard.data.model

import java.util.ArrayList
import java.util.Random

/**
 * Created by Thomas Skogemann on 14-10-2016.
 */
object Utils {

    val randomString: String
        get() {
            val strings = ArrayList<String>()
            strings.add("Faxe kondi")
            strings.add("Coca Cola")
            strings.add("Pepsi")
            strings.add("Fanta")
            strings.add("7UP")
            strings.add("Pepsi max")
            strings.add("Cola Light")
            val random = Random()
            return strings[random.nextInt(strings.size)]
        }

    fun getRandomBoolean(i: Int): Boolean {
        if (i == 0) {
            return true
        } else
            return false

        /*  can be used to get a random boolean
        Random random = new Random();
        int rnd1 = random.nextInt(7);
        int rnd2 = random.nextInt(7);
        if (rnd1 >= rnd2) {
            return false;
        } else return true;
        */
    }
}
