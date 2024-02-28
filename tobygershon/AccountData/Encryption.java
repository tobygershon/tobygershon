import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Encryption {



        private static final Map<String, Integer> LETTER_MAP = new HashMap<>();
        private static final Map<Integer, String> NUMBER_MAP = new HashMap<>();

        private static final String[] CHARACTERS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "!", "@", "#", "$", "%"};
        private static final Integer[] NUMBERS = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58};
        private static final Integer[] CYPHER = new Integer[]{5, 18, 4, 9, 10, 16, 3, 1, 2, 2, 8, 20, 31, 48, 7, 17, 12, 24, 27, 19};

//    method to build letter/symbol map

        private static Map<String, Integer> letterToNumberHashMapBuild() {
            for (int i = 0; i < CHARACTERS.length; i++) {
                LETTER_MAP.put(CHARACTERS[i], NUMBERS[i]);
            }
            return LETTER_MAP;
        }
        private static Map<Integer, String> numberToLetterHashMapBuild() {
            for (int i = 0; i < CHARACTERS.length; i++) {
                NUMBER_MAP.put(NUMBERS[i], CHARACTERS[i]);
            }
            return NUMBER_MAP;
        }

        //    encryption method
        private String encryptPasswords(String password, String username) {
            String[] nameArray = username.split(" ");  //splits full name by spaces to separate first/last name
            int firstLength = nameArray[0].length();
            String[] firstSplit = nameArray[0].split("");
//        add complexity of altered cyper with last name later if desired
            //        int lastName = nameArray[nameArray.length -1].length();
            //        String[] lastSplit = nameArray[nameArray.length -1].split("");

            // alter CYPHER array by using first name to alter corresponding indexes by values of characters in first name
//        then do the same with last name but start from the end of the cypher to alter it
            Map<String, Integer> newLetterMap = letterToNumberHashMapBuild();
            Map<Integer, String> newNumberMap = numberToLetterHashMapBuild();

            Integer[] newCypher = new Integer[CYPHER.length];
            for (int i = 0; i < firstSplit.length; i++) {
                if (CYPHER[i] + newLetterMap.get(firstSplit[i]) <= newLetterMap.keySet().size()) {
                    newCypher[i] = CYPHER[i] + newLetterMap.get(firstSplit[i]);
                } else {
                    newCypher[i] = (CYPHER[i] + newLetterMap.get(firstSplit[i]) - newLetterMap.keySet().size()); // if the addition of the 2 nums is > size of the keySet, subtract size of keyset from the sum to get the integer value of the encrypted letter
                }
            }
//        then use new cypher for each individual to encrypt their password
            String[] passwordArray = password.split("");
            String encryptedPassword = "";
            for (int i = 0; i < passwordArray.length; i++) {
                int passwordNum = newLetterMap.get(passwordArray[i]);
                int cypherNum = newCypher[i];
                int newNum = passwordNum + cypherNum;
                if (newNum <= newLetterMap.keySet().size()) {
                    encryptedPassword += newNumberMap.get(newNum); //gets the character value from the new number key, adds it to string
                } else {
                    newNum = (passwordNum + cypherNum) - newLetterMap.keySet().size();
                    encryptedPassword += newNumberMap.get(newNum);
                }

            }
            return encryptedPassword;
        }

        private String reverseEncryptPassword(String password, String username) {
//        TODO: complete reverse encryption
            return null;
        }


    }

