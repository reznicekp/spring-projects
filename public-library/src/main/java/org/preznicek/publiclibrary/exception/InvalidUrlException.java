package org.preznicek.publiclibrary.exception;

/**
 * Pouziva se zejmena v pripade, kdy se vstupni parametr funkce ocekava ciselny, ale pri validaci 
 * se zjisti, ze ciselny neni.<br>
 * Vyhozeni teto vyjimky signalizuje vykresleni soukromeho menu (po prihlaseni).
 */
public class InvalidUrlException extends Exception {

}
