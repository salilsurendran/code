package com.salil.todoist;

import com.salil.todoist.entities.Label;
import com.salil.todoist.entities.Project;
import com.salil.todoist.entities.Task;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.*;

public class TodoistMain {

    private static final String API = "API";
    private static final String URL = "https://todoist.com";
    private static final String[] SEARCH_LABEL = new String[]{"next_action",
            "waiting_for"};
    private static final String[] EXCLUDED_PROJECTS = new String[]{"Low Priority"};

    public static void main(String args[]) {

        if (args.length == 0) {
            System.out.println("Enter your token as the first argument");
            return;
        }
        String TOKEN = args[0];
        GenericType<List<Project>> projectList = new GenericType<List<Project>>() {
        };
        Client client = ClientBuilder.newClient().register(JacksonFeature.class);
        WebTarget target = client.target(URL).path(API)
                .queryParam("token", TOKEN);

        List<Project> projects = target.path("getProjects")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get(projectList);

        List<Long> searchLabelId = getNextActionLabelId(target);
        // System.out.println("Label Id : " + searchLabelId);

        List<String> excludedProjects = Arrays.asList(EXCLUDED_PROJECTS);
        for (Project project : projects) {
            /*
			 * System.out.println("Name : " + project.getName() + " Id : " +
			 * project.getId());
			 */
            if (!excludedProjects.contains(project.getName())) {
                List<Task> listOfTasks = target.path("getUncompletedItems")
                        .queryParam("project_id", project.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Task>>() {
                        });

                Queue<Task> taskList = new LinkedList<Task>(listOfTasks);
                while (!taskList.isEmpty()) {
                    Task currentTask = taskList.poll();
                    if (!currentTask.isChecked() && !currentTask.hasLabel(searchLabelId)
                            && !checkForLabel(searchLabelId, taskList))
                        System.out.println(project.getName() + " : " + currentTask.getContent());
                }
            }

        }

    }

    private static boolean checkForLabel(List<Long> searchLabelIds,
                                         Queue<Task> taskList) {
        boolean found = false;

        while (!taskList.isEmpty()) {
            Task task = taskList.peek();
            if (task.getIndent() == 1)
                return found;
            else if (!found)
                found = task.hasLabel(searchLabelIds) && !task.isChecked();
            taskList.remove();
        }
        return found;
    }

    private static List<Long> getNextActionLabelId(WebTarget target) {
        List<Long> searchLabelIds = new ArrayList<Long>();
        List<String> searchLabelNames = Arrays.asList(SEARCH_LABEL);
        Map<String, Label> labelMap = target.path("getLabels")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Map<String, Label>>() {
                });
        for (Label label : labelMap.values())
            if (searchLabelNames.contains(label.getName())) {
                searchLabelIds.add(label.getId());
                //searchLabelNames.remove(label.getName());
            }

		/*if (!searchLabelNames.isEmpty())
			throw new RuntimeException(searchLabelNames.toString()
					+ " not found in labels");*/
        return searchLabelIds;
    }

}
