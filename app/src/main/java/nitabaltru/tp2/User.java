package nitabaltru.tp2;

/**
 * A class representing the user
 * Created by nitabaltru on 04/12/2017.
 */

class User {

    /**
     * the user's name
     */
    private String name;

    /**
     * the user's email
     */
    private String email;

    /**
     * constructor
     * @param name the user's name
     * @param email the user's email
     */
    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getEmail() {
        return email;
    }

    String getName() {
        return name;
    }
}
