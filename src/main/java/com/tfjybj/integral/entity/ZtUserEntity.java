package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * ZtUser实体
 * ztUser
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "ZtUserEntity:ztUser")
@Data
@TableName(value = "zt_user")
public class ZtUserEntity implements Serializable {

	/**
	 * dept
	 */
	@ApiModelProperty(value = "dept",required = true)
	@Column(name = "dept")
	private Integer dept;

	/**
	 * account
	 */
	@ApiModelProperty(value = "account",required = true)
	@Column(name = "account")
	private String account;

	/**
	 * password
	 */
	@ApiModelProperty(value = "password",required = true)
	@Column(name = "password")
	private String password;

	/**
	 * role
	 */
	@ApiModelProperty(value = "role",required = true)
	@Column(name = "role")
	private String role;

	/**
	 * realname
	 */
	@ApiModelProperty(value = "realname",required = true)
	@Column(name = "realname")
	private String realname;

	/**
	 * nickname
	 */
	@ApiModelProperty(value = "nickname",required = true)
	@Column(name = "nickname")
	private String nickname;

	/**
	 * commiter
	 */
	@ApiModelProperty(value = "commiter",required = true)
	@Column(name = "commiter")
	private String commiter;

	/**
	 * avatar
	 */
	@ApiModelProperty(value = "avatar",required = true)
	@Column(name = "avatar")
	private String avatar;

	/**
	 * birthday
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "birthday",required = true)
	@Column(name = "birthday")
	private Date birthday;

	/**
	 * gender
	 */
	@ApiModelProperty(value = "gender",required = true)
	@Column(name = "gender")
	private String gender;

	/**
	 * email
	 */
	@ApiModelProperty(value = "email",required = true)
	@Column(name = "email")
	private String email;

	/**
	 * skype
	 */
	@ApiModelProperty(value = "skype",required = true)
	@Column(name = "skype")
	private String skype;

	/**
	 * qq
	 */
	@ApiModelProperty(value = "qq",required = true)
	@Column(name = "qq")
	private String qq;

	/**
	 * yahoo
	 */
	@ApiModelProperty(value = "yahoo",required = true)
	@Column(name = "yahoo")
	private String yahoo;

	/**
	 * gtalk
	 */
	@ApiModelProperty(value = "gtalk",required = true)
	@Column(name = "gtalk")
	private String gtalk;

	/**
	 * wangwang
	 */
	@ApiModelProperty(value = "wangwang",required = true)
	@Column(name = "wangwang")
	private String wangwang;

	/**
	 * mobile
	 */
	@ApiModelProperty(value = "mobile",required = true)
	@Column(name = "mobile")
	private String mobile;

	/**
	 * phone
	 */
	@ApiModelProperty(value = "phone",required = true)
	@Column(name = "phone")
	private String phone;

	/**
	 * address
	 */
	@ApiModelProperty(value = "address",required = true)
	@Column(name = "address")
	private String address;

	/**
	 * zipcode
	 */
	@ApiModelProperty(value = "zipcode",required = true)
	@Column(name = "zipcode")
	private String zipcode;

	/**
	 * join
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "join",required = true)
	@Column(name = "join")
	private Date join;

	/**
	 * visits
	 */
	@ApiModelProperty(value = "visits",required = true)
	@Column(name = "visits")
	private Integer visits;

	/**
	 * ip
	 */
	@ApiModelProperty(value = "ip",required = true)
	@Column(name = "ip")
	private String ip;

	/**
	 * last
	 */
	@ApiModelProperty(value = "last",required = true)
	@Column(name = "last")
	private Integer last;

	/**
	 * fails
	 */
	@ApiModelProperty(value = "fails",required = true)
	@Column(name = "fails")
	private Integer fails;

	/**
	 * locked
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "locked",required = true)
	@Column(name = "locked")
	private Date locked;

	/**
	 * ranzhi
	 */
	@ApiModelProperty(value = "ranzhi",required = true)
	@Column(name = "ranzhi")
	private String ranzhi;

	/**
	 * deleted
	 */
	@ApiModelProperty(value = "deleted",required = true)
	@Column(name = "deleted")
	private String deleted;


}
