package com.plat.acoal.dao;


import com.plat.acoal.model.AccessToken;

public interface AccessTokenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_accesstoken
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_accesstoken
     *
     * @mbggenerated
     */
    int insert(AccessToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_accesstoken
     *
     * @mbggenerated
     */
    int insertSelective(AccessToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_accesstoken
     *
     * @mbggenerated
     */
    AccessToken selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_accesstoken
     *
     * @mbggenerated
     */

    int updateByPrimaryKeySelective(AccessToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_accesstoken
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AccessToken record);
}
