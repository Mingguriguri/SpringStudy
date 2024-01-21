package com.todoschedule.dao;
import com.todoschedule.dto.UserDTO;

public interface UserDAO {
	public String loginCheck(UserDTO dto);
}
