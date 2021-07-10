package com.nathans.steam.Model;

public class ModelWallet {

    String _id, walletCode, walletType, walletPrc, pics;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWalletCode() {
        return walletCode;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getWalletPrc() {
        return walletPrc;
    }

    public void setWalletPrc(String walletPrc) {
        this.walletPrc = walletPrc;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}
