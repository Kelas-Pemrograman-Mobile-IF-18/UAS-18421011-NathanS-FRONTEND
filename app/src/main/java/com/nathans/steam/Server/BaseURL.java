package com.nathans.steam.Server;

public class BaseURL {

    public static String baseUrl = "http://192.168.100.19:6969/";


    public static String signin = baseUrl + "user/signin";
    public static String signup = baseUrl + "user/signup";

    //Steam Wallet
    public static String dataWallet   = baseUrl + "wallet/dataWallet";
    public static String updateWallet = baseUrl + "wallet/update/";
    public static String deleteWallet = baseUrl + "wallet/delete/";
    public static String inputWallet  = baseUrl + "wallet/input/";

    //Steam Game
    public static String dataGame   = baseUrl + "game/dataGame";
    public static String updateGame = baseUrl + "game/update/";
    public static String deleteGame = baseUrl + "game/delete/";
    public static String inputGame  = baseUrl + "game/input/";

    //Steam Cart
    public static String dataCart   = baseUrl + "cart/dataCart";
    public static String inputCart  = baseUrl + "cart/input/";
    public static String deleteCart = baseUrl + "cart/delete/";
}
