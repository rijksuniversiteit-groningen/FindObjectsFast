package nl.rug.blackboard.findObjectsFast.controllers;


import blackboard.data.user.User;
import blackboard.platform.plugin.PlugIn;
import blackboard.platform.plugin.PlugInManager;
import blackboard.platform.plugin.PlugInManagerFactory;
import blackboard.platform.spring.beans.annotations.ContextValue;
import nl.rug.blackboard.findObjectsFast.search.SearchManager;
import nl.rug.blackboard.findObjectsFast.search.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ViewModuleController {
	@RequestMapping(value = "/viewModule")
	public String viewModel(Model model, @ContextValue User user) {
//		if(User.SystemRole.SYSTEM_ADMIN != user.getSystemRole()) {
//			throw new RuntimeException("Only system admin can use 'Find Objects Fast'");
//		}

		PlugInManager plugInManager = PlugInManagerFactory.getInstance();
		PlugIn plugIn = plugInManager.getPlugIn("UG", "FindObjectsFast");
		String contextPath = plugInManager.getContextPath(plugIn);
		model.addAttribute("contextPath", contextPath);
		return "viewModule";
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public SearchResult search(@RequestParam String searchTerm) {
		SearchManager searchManager = new SearchManager();
		return searchManager.search(searchTerm);
	}
}