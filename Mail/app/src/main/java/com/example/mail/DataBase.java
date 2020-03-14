package com.example.mail;


public class DataBase {
    private static final Email[] emailList  = {
            new Email("Sam", "Weekend adventure",
                    "Let's go fishing with John and others. We will do some barbecue and have so much fun",
                    "10:42am"),
            new Email("Facebook", "James, you have 1 new notification",
                    "A lot has happened on Facebook since", "16:04pm"),
            new Email("Google+", "Top suggested Google+ pages for you",
                    "Top suggested Google+ pages for you", "18:44pm"),
            new Email("Twitter", "Follow T-Mobile, Samsung Mobile U",
                    "James, some people you may know","20:04pm"),
            new Email("Pinterest Weekly", "Pins you’ll love!",
                    "Have you seen these Pins yet? Pinterest","09:04am"),
            new Email("Josh", "Going lunch",
                    "Don't forget our lunch at 3PM in Pizza hut","01:04am"),
            new Email("Sam", "Weekend adventure",
                    "Let's go fishing with John and others. We will do some barbecue and have so much fun",
                    "10:42am"),
            new Email("Facebook", "James, you have 1 new notification",
                    "A lot has happened on Facebook since","16:04pm"),
            new Email("Google+", "Top suggested Google+ pages for you",
                    "Top suggested Google+ pages for you","18:44pm"),
            new Email("Twitter", "Follow T-Mobile, Samsung Mobile U",
                    "James, some people you may know","20:04pm"),
            new Email("Pinterest Weekly", "Pins you’ll love!",
                    "Have you seen these Pins yet? Pinterest","09:04am"),
            new Email("Josh", "Going lunch",
                    "Don't forget our lunch at 3PM in Pizza hut","01:04am")
};


    public static Email[] getEmailList() {
        return emailList;
    }

}
