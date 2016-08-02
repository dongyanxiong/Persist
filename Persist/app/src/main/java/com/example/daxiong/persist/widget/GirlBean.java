package com.example.daxiong.persist.widget;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/27.
 * {
 "_id": "57981ee6421aa90d36e96090",
 "createdAt": "2016-07-27T10:39:34.818Z",
 "desc": "\u738b\u5b50\u6587",
 "publishedAt": "2016-07-27T11:27:16.610Z",
 "source": "chrome",
 "type": "\u798f\u5229",
 "url": "http://ww3.sinaimg.cn/large/610dc034jw1f689lmaf7qj20u00u00v7.jpg",
 "used": true,
 "who": "\u4ee3\u7801\u5bb6"
 }
 */
public class GirlBean implements Serializable{
    public String _id ;
    public String createdAt ;
    public String desc ;
    public String publishedAt ;
    public String source ;
    public String type ;
    public String url ;
    public String used ;
    public String who ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "GirlBean{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used='" + used + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
}
