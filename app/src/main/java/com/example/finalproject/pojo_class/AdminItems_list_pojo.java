package com.example.finalproject.pojo_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminItems_list_pojo {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("item_list")
    @Expose
    private List<ItemList> itemList = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    public class ItemList {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("no_of_items")
        @Expose
        private Integer noOfItems;
        @SerializedName("type")
        @Expose
        private Integer type;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("item_issued_status")
        @Expose
        private Integer itemIssuedStatus;
        @SerializedName("issued_item_user_id")
        @Expose
        private Integer issuedItemUserId;
        @SerializedName("issued_date")
        @Expose
        private String issuedDate;
        @SerializedName("return_date")
        @Expose
        private String returnDate;
        @SerializedName("user_list")
        @Expose
        private List<UserList> userList = null;
        @SerializedName("issued_item")
        @Expose
        private List<Object> issuedItem = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getNoOfItems() {
            return noOfItems;
        }

        public void setNoOfItems(Integer noOfItems) {
            this.noOfItems = noOfItems;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getItemIssuedStatus() {
            return itemIssuedStatus;
        }

        public void setItemIssuedStatus(Integer itemIssuedStatus) {
            this.itemIssuedStatus = itemIssuedStatus;
        }

        public Integer getIssuedItemUserId() {
            return issuedItemUserId;
        }

        public void setIssuedItemUserId(Integer issuedItemUserId) {
            this.issuedItemUserId = issuedItemUserId;
        }

        public String getIssuedDate() {
            return issuedDate;
        }

        public void setIssuedDate(String issuedDate) {
            this.issuedDate = issuedDate;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
        }

        public List<UserList> getUserList() {
            return userList;
        }

        public void setUserList(List<UserList> userList) {
            this.userList = userList;
        }

        public List<Object> getIssuedItem() {
            return issuedItem;
        }

        public void setIssuedItem(List<Object> issuedItem) {
            this.issuedItem = issuedItem;
        }

    }
    public class UserList{
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("usertype")
        @Expose
        private String usertype;
        @SerializedName("session_id")
        @Expose
        private String sessionId;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

    }
    }
