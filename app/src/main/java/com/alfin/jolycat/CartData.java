package com.alfin.jolycat;

public class CartData {
    private String cartID;
    private String catID;
    private String checkoutID;
    private String userID;
    private int quantity;

    private Cats cats;
    private User users;

    public CartData(Cats cats, User users, String cartID, String checkoutID, int quantity) {
        this.cats = cats;
        this.users = users;

        this.cartID = cartID;
        this.catID = getCatID();
        this.checkoutID = checkoutID;
        this.userID = getUserID();
        this.quantity = quantity;
    }

    public String getCatID() {
        return cats.getCatID();
    }

    public String getUserID() {
        return users.getUserID();
    }

    public String getCartID() {
        return cartID;
    }

    public String getCheckoutID() {
        return checkoutID;
    }
}
