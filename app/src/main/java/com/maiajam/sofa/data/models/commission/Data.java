
package com.maiajam.sofa.data.models.commission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("commissions")
    @Expose
    private Integer commissions;
    @SerializedName("payments")
    @Expose
    private Integer payments;
    @SerializedName("net_commissions")
    @Expose
    private Integer netCommissions;
    @SerializedName("commission")
    @Expose
    private String commission;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCommissions() {
        return commissions;
    }

    public void setCommissions(Integer commissions) {
        this.commissions = commissions;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public Integer getNetCommissions() {
        return netCommissions;
    }

    public void setNetCommissions(Integer netCommissions) {
        this.netCommissions = netCommissions;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

}
