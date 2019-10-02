package com.sudip.sms.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sudip.sms.dto.UserDto;
import com.sudip.sms.util.DbUtil;

public class UserDao {
	PreparedStatement ps=null;

		public  void saveUserInfo(UserDto userDto ){
		String sql="insert into student_data (name,password,address, email,dob,phone,gender,country,image_url)values(?,?,?,?,?,?,?,?,?)";
		  
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, userDto.getName());
			ps.setString(2,userDto.getPassword());
			ps.setString(3, userDto.getAddress());
			ps.setString(4, userDto.getEmail());
			ps.setDate(5, new Date(userDto.getDob().getTime()) );
			ps.setLong(6, userDto.getPhone());
			ps.setString(7, userDto.getGender());
			ps.setString(8,userDto.getCountry());
			ps.setString(9, userDto.getImageUrl());
			ps.executeUpdate();
			System.out.println("data inseted");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

		public List<UserDto> searchdDetail(String name){
			
			List<UserDto> userDtos = new ArrayList<>();
			String sql="select * from student_data where name like ?";
			try {
				ps=DbUtil.getConnection().prepareStatement(sql);
				ps.setString(1, "%"+name+"%");
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					UserDto userDto = new UserDto();
					userDto.setId(rs.getInt("id"));
					userDto.setAddress(rs.getString("address"));
					userDto.setCountry(rs.getString("country"));
					userDto.setDob(rs.getDate("dob"));
					userDto.setEmail(rs.getString("email"));
					userDto.setGender(rs.getString("gender"));
					userDto.setName(rs.getString("name"));
					userDto.setPassword(rs.getString("password").toCharArray());
					userDto.setPhone(rs.getLong("phone"));
					userDto.setImageUrl(rs.getString("image_url"));
					userDtos.add(userDto);
					
					
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return userDtos;
		}
		public boolean login(String username,String  password){
			
			String sql="select * from student_data where name = ?";
			try {
				ps=DbUtil.getConnection().prepareStatement(sql);
				ps.setString(1, username);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					if(rs.getString("password").equals(password)){
						return true;
					}
				}
				}catch(Exception e){
					
				}
			return false;
		}
		public  void updateUserInfo(UserDto userDto ){
		String sql="update student_data set name=?,address=?, email=?,dob=?,phone=?,gender=?,country=?,image_url=? where id=?";
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, userDto.getName());
			
			ps.setString(2, userDto.getAddress());
			ps.setString(3, userDto.getEmail());
			ps.setDate(4, new Date(userDto.getDob().getTime()) );
			ps.setLong(5, userDto.getPhone());
			ps.setString(6, userDto.getGender());
			ps.setString(7,userDto.getCountry());
			ps.setString(8, userDto.getImageUrl());
			ps.setInt(9, userDto.getId());
			
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

		public List<UserDto> getAlldetail(){
		
			List<UserDto> userDtos = new ArrayList<>();
			String sql="select * from student_data";
			try {
				ps=DbUtil.getConnection().prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					UserDto userDto = new UserDto();
					userDto.setId(rs.getInt("id"));
					userDto.setAddress(rs.getString("address"));
					userDto.setCountry(rs.getString("country"));
					userDto.setDob(rs.getDate("dob"));
					userDto.setEmail(rs.getString("email"));
					userDto.setGender(rs.getString("gender"));
					userDto.setName(rs.getString("name"));
					userDto.setPassword(rs.getString("password").toCharArray());
					userDto.setPhone(rs.getLong("phone"));
					userDto.setImageUrl(rs.getString("image_url"));
					userDtos.add(userDto);
					
					
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return userDtos;
		}
		public void  deletData(int id){
			String sql="delete from student_data where id=? ";
			try {
				ps=DbUtil.getConnection().prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	
}
