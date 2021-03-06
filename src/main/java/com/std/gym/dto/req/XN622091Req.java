package com.std.gym.dto.req;

/**
 * 修改私教信息
 * @author: asus 
 * @since: 2017年7月18日 下午12:31:26 
 * @history:
 */
public class XN622091Req {
    // 编号
    private String code;

    // 真实姓名
    private String realName;

    // 缩略图
    private String pic;

    // 性别（1 男，0 女）
    private String gender;

    // 年龄
    private String age;

    // 工作年限
    private String duration;

    // 标签
    private String label;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
