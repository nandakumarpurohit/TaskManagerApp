package com.marlabs.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.dao.TaskManagerService;
import com.marlabs.domain.Task;


@RestController
public class TaskManagerController {
	
	TaskManagerService taskmanagerservice=new TaskManagerService();
	
	/**
	 * 
	 * @return
	 */
	 @RequestMapping(value="/tasks",method = RequestMethod.GET,headers={"Accept=application/json","Access-Control-Allow-Origin=*"})
	 @ResponseBody
	 public List<Task> getAllTasks() {	 
	  List<Task> tasks=taskmanagerservice.getAllTasks();
	  return tasks;
	
	 }
	 
	 /**
	  * 
	  * @param taskIds
	  * @return
	  */
	 @RequestMapping(value="/tasks/archive/{taskIds}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> archiveAllTasks(@PathVariable int[] taskIds) {	
		 for(int i=0;i<taskIds.length;i++){
			 taskmanagerservice.archiveTask(taskIds[i]);	
			 
		 }
	  List<Task> tasks=taskmanagerservice.getAllTasks();
	  return tasks;
	
	 }
	 
	 /**
	  * 
	  * @param taskId
	  * @param taskStatus
	  * @return
	  * @throws ParseException
	  */
	 @RequestMapping(value="/tasks/{taskId}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> changeTaskStatus(@PathVariable int taskId,@PathVariable String taskStatus) throws ParseException {	
		 taskmanagerservice.changeTaskStatus(taskId,taskStatus);		 
		 return taskmanagerservice.getAllTasks();
		 
	 }
	 
	 /**
	  * 
	  * @param taskName
	  * @param taskDesc
	  * @param taskPriority
	  * @param taskStatus
	  * @return
	  * @throws ParseException
	  */
	 @RequestMapping(value="/tasks/insert/{taskName}/{taskDesc}/{taskPriority}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> addTask(@PathVariable String taskName,@PathVariable String taskDesc,@PathVariable String taskPriority,@PathVariable String taskStatus) throws ParseException {	
		Task task = new Task();
		task.setTaskName(taskName);
		task.setDescription(taskDesc);
		task.setPriority(taskPriority);
		task.setStatus(taskStatus);
		taskmanagerservice.addTask(task);
		return taskmanagerservice.getAllTasks();
		 
	 }	 	 	 	 
}