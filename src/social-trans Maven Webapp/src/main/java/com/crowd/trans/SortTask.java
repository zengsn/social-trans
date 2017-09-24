package com.crowd.trans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.crowd.bean.AcceptTask;
import com.crowd.service.AcceptTaskService;
import com.crowd.service.RoleService;
import com.crowd.service.UserService;
@Controller
public class SortTask {
	@Autowired
	private static AcceptTaskService acceptTaskService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
//	static AcceptTaskService acceptTaskService = new AcceptTaskService();
//	RoleService roleService = new RoleService();
//	UserService userService = new UserService();
	public static void main(String[] args) {
		int level1 = 3;
		int level2 = 1;
		int good1Num = 3;
		int good2Num = 12;
				
		
					if( level2-level1==1){
						if(good2Num==0&&good1Num-good2Num<=3||good1Num/good2Num<=3){
							System.out.println("level2-level1 1 +1");
						}
						else{
							System.out.println("level2-level1 1 -1");
						}
					}
					else if (level2-level1==2 ){
						if(good2Num==0&&good1Num-good2Num<=5||good1Num/good2Num<=5){
							System.out.println("level2-level1 2 +1");
						}
						else{
							System.out.println("level2-level1 2 -1");
						}
					}
//						return 1;
					else if (level1-level2==1){
						if(good1Num==0&&good2Num-good1Num<=3||good2Num/good1Num<=3){
							System.out.println("level1-level2==1 +1");
						}
						else{
							System.out.println("level1-level2==1 -1");
						}
					}
					else if (level1-level2==2){
						if(good1Num==0&&good2Num-good1Num<=5||good2Num/good1Num<=5){
							System.out.println("level1-level2==2 +1");
						}
						else{
							System.out.println("level1-level2==2 -1");
						}
					}
					else {
						
						if (good1Num < good2Num)
							System.out.println("good1Num < good2Num");
						else if (good1Num > good2Num)
							System.out.println("good1Num > good2Num");
						else
							System.out.println("0");
					}
				}

			
	

	public List<AcceptTask> sortAccept(List<AcceptTask> acList){
		// 排序
		Collections.sort(acList, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				AcceptTask actask1 = (AcceptTask) o1;
				AcceptTask actask2 = (AcceptTask) o2;
				System.out.println(actask1.getUserId());
				System.out.println(userService
						.selectUserById(actask1.getUserId()));
				String roleId1 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask1.getUserId()).getRole());
				System.out.println(roleId1);
				int good1Num = actask1.getGoods();
				int good2Num = actask2.getGoods();
				System.out.println(actask2.getUserId());
				String roleId2 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask2.getUserId()).getRole());
				System.out.println(roleId2);
				int level1 = roleService.getLevelByRoleId(roleId1);
				int level2 = roleService.getLevelByRoleId(roleId2);
				if( level2-level1==1){
					if((good2Num==0&&good1Num-good2Num<=3)||good1Num/good2Num<=3){
						return 1;
					}
					else{
						return -1;
					}
				}
				else if (level2-level1==2 ){
					if((good2Num==0&&good1Num-good2Num<=5)||good1Num/good2Num<=5){
						return 1;
					}
					else{
						return -1;
					}
				}
//					return 1;
				else if (level1-level2==1){
					if((good1Num==0&&good2Num-good1Num<=3)||good2Num/good1Num<=3){
						return -1;
					}
					else{
						return 1;
					}
				}
				else if (level1-level2==2){
					if((good1Num==0&&good2Num-good1Num<=5)||good2Num/good1Num<=5){
						return -1;
					}
					else{
						return 1;
					}
				}
				else {
					
					if (good1Num < good2Num)
						return 1;
					else if (good1Num > good2Num)
						return -1;
					else
						return 0;
				}
			}

		});
		return acList;
	}
}	
