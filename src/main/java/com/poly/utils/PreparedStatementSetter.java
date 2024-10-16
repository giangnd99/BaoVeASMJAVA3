/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Computer
 */
public interface PreparedStatementSetter<T> {
    void setValues(PreparedStatement ps, T entity)throws SQLException;
}
