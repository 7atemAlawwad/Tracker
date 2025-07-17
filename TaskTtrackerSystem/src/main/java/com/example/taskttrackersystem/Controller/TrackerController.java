package com.example.taskttrackersystem.Controller;

import com.example.taskttrackersystem.API.ApiResponse;
import com.example.taskttrackersystem.Model.Tracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {

    ArrayList<Tracker> trackerList = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Tracker> getTrackerList() {
        return trackerList;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTrackerList(@PathVariable int index, @RequestBody Tracker tracker) {
        trackerList.set(index, tracker);
        return new ApiResponse("Tracker updated successfully", "200");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTracker(@PathVariable int index) {
        trackerList.remove(index);
        return new ApiResponse("Tracker deleted successfully", "200");
    }

    @PutMapping("/changeStatus/{index}")
    public ApiResponse changeStatus(@PathVariable int index, @RequestBody String status) {
        trackerList.get(index).setStatus(status);
        return new ApiResponse("Status changed successfully", "200");
    }

    @GetMapping("/search")
    public ApiResponse searchTracker(@RequestBody String title) {

        for (Tracker tracker : trackerList) {
            if (tracker.getTitle().equals(title)) {
                return new ApiResponse("Tracker found successfully", "200");
            }
            else {
                return new ApiResponse("Tracker not found", "404");
            }
        }
        return new ApiResponse("Tracker not found", "404");
    }

}
