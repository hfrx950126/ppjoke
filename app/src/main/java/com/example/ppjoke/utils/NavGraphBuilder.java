package com.example.ppjoke.utils;

import android.content.ComponentName;

import com.example.ppjoke.model.Destination;

import java.util.HashMap;

import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

public class NavGraphBuilder {
    public static void build(NavController controller) {
        NavigatorProvider provider = controller.getNavigatorProvider();

        FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);

        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));


        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();

        for (Destination value : destConfig.values()) {
            if (value.isFragment) {
                FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setClassName(value.clazName);
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);

            } else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), value.clazName));
                navGraph.addDestination(destination);
            }
            if (value.asStarter) {
                navGraph.setStartDestination(value.id);
            }
        }
        controller.setGraph(navGraph);


    }
}
