package com.mm.market.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat/**")
public class MainController {
	
	List<RoomVO> roomList = new ArrayList<RoomVO>();
	static int roomNumber = 0;
	
	@GetMapping("chatPage")
	public ModelAndView chat() throws  Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/chatPage");
		return mv;
	}
	
	/**방페이지
	 * @return
	 */
	@GetMapping("room")
	public ModelAndView room() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/room");
		return mv;
	}
	
	/** 방 생성하기 
	 * @param params
	 * @return
	 */
	@GetMapping("createRoom")
	public @ResponseBody List<RoomVO> createRoom(@RequestParam HashMap<Object, Object> params) {
		String roomName = (String)params.get("roomName");
		if(roomName!=null && !roomName.trim().equals("")) {
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomNumber(++roomNumber);
			roomVO.setRoomName(roomName);
			roomList.add(roomVO);
		}
		return roomList; 
	}
	
	
	/** 방 정보 가져오기
	 * @param params
	 * @return
	 */
	@GetMapping("getRoom")
	 public @ResponseBody List<RoomVO> getRoom(@RequestParam HashMap<Object, Object> params) {
		 return roomList;
	 }
	
	/** 채팅방
	 * @return
	 */
	@GetMapping("moveChating")
	public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
		ModelAndView mv = new ModelAndView();
		int roomNumber = Integer.parseInt((String)params.get("roomNumber"));
		
		List<RoomVO> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
		if(new_list != null && new_list.size()>0) {
			mv.addObject("roomName", params.get("roomName"));
			mv.addObject("roomNumber", params.get("roomNumber"));
			mv.setViewName("chatPage");
		} else {
			mv.setViewName("room");
		}
		return mv;
	}
}
