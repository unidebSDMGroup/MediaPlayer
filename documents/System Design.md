# System Design

## I . Core Objectives.

At the very basic functional level the product should deliver the following core functionalities.

- Working application in a windows environment.
- Import multiple videos/images/audio from files with preset file types.
- Allow editing of size, timeline location of imported media elements.
- Export the combined video into a video file.

## II . Project Phases.

The timeline was formulated based on the SCRUM model with each sprint lasting approximately 3 weeks.

| Sprint     | Development Period |
| ----------- | -----------|
| Initial stages     | 10 oct - 23 oct |
|   Plan implementation    | 24 oct - 6 nov |
|    Project skeleton and main features   | 7 nov - 20 nov |
|    Additional features  | 21 nov - 27 nov |
|    Finalizing   | 28 nov - 4 dec |


#### 1. Initial stages
This stage consists of ideas and resource gathering.
The main concept, features, as well as the general will also be planned in this stage.

#### 2. Plan implementation
Here we will properly set up our development environment, configure our tools and make sure all team members are set-up. This is also where work on the system design document starts.
#### 3. Project skeleton and main Features
This stage will contain progress towards creating and maintaining a basic working copy of our envisioned software. Major features such as screens, basic controller logic, and basic business will have to be added and tested.
Stub and Driver methods/functions could be added to facilitate implementation.
#### 4. Additional Features
This stage further progresses the development with additional but required features.
This ranges from unique color assignment to validator methods used to check if the render conditions are met. While these changes are not entirely optional, they need to be added to ensure full software functionality.
This is also where major bug fixes take place.
#### 5. Finalizing
The wrap-up stage is where we make sure everything is implemented properly. Minor bug fixes and UI tests will have to be preformed.
Any non major and non software-breaking quality of life changes could also be added  and tested here.

## III . Theoretical Development Strategy.

- SCRUM model for development cycles.
- Project Building using Maven.
- Media Application using JavaFX .
- Testing JUnit and FX-Testing.

## IV . Theoretical Feature List.

### 1 - General Features.

- Application window: Two seperate windows on launch for syncing and plotting.
	1. Timeline window.(Refer to Visuals and UI Features)
	2. Preview Render window.
	3. Export window.

- Both of these combined refer to a mediaObject object with all the media file related attributes and functions.

- All media objects are then stored in an array.

#### Preview Render Window:

	- This window is more of a preview of the arrangment of the imported media files.
	- Display imported media/ allow user arrangement (Render x/y coordinates from mediaObject).

#### Export Window:

	- Displays the final exported media file.

### 2 - Visuals and UI Features.

#### Scene:

Timeline/Media Stack Window(stackPane & scrollPane):

	- The timeline window will have a video specific timeline bar (based on videolength) for each video that is imported.
	- Zone Selector: Overlayed on the scrollpane window) used to set the timeline duration.
	- Scroll pane: Has media object related data and will include the timeline bar for each video object imported.
	- Stack pane: Also linked to the media object attributes.

Import Export Buttons:

	- Buttons part of the scene but not attached to mediaObject.

## V. Technical Development Strategy.

Hierarchy of the application loop:

1. Primary Stage.

	- Timeline Attributes :
		- Main application buttons(importing exporting, closing, minimizing  included in JavaFX).
		- Information about imported media count.
		- Information about existing media:(From initializing mediaObject)
			- Names and color codes / thumbnails.
			- isMmuted.
			- isVisible.
			- Start, end and duration values.
		- Information about which section of the video to be rendered (zone select).
		- Information about the timeline grid.

	- Timeline Functions :
		- Storing some visual nodes from the imported media objects.
		- Changing start and end positions (time) of imported media objects(from mediaObject)
		- Rearrange media layers.(Imported media files ordered in an array?)
		- Hide a media ( isHidable boolean? )
		- Mute a media ( isMutable boolean? )
		- Select which zone to render ( using 2 lines from zone select returns x values from the pane set by the user).

	- Timeline Constraints :
		- Start zone line position cannot be less than right edge or more than end line.
		- End zone line position cannot be more than left edge or less than start line.
		- Media boxes position cannot be less than right edge.(x y edge constraints on the pane)
		- Images are not mutable.(mute attribute not inherited)
		- Audio is not hide-able.(hide attribute not inherited)
		- Cannot have more than 8 media at the same time.(inherent limitation of JavaFX?)

2. Import Stage.

	- Preview Attributes :
		- Information about media objects position(x y position within preview pane).
		- Information about media objects size.
		- Color codes / thumbnail for identification (mediaObject attribute?).

	- Preview Permits :
		- Resizing boxes.
		- Moving boxes. (onhover or onclick functions to resize? mouse co-ordinates to move?)
		- Updating media objects with user imposed info ( from boxes ).
		- Snapping boxes to other boxes.(same as mousehover snap to edge?)
		- Shift-move to move in 1 direction.(if button held snap to axis)

	- Preview Constrains :
		- Media cannot overlap.(coordinate check condition between media objects for overlap)
		- Media cannot leave borders.(border coordinate check condition )


3. Render Stage.

	- Export Includes :
		- Information about rendered media.
	- Export Permits :
		- Pausing, playing and other basic video functionalities (JavaFX).

Note: This is based on how it functions currently and is not extensive.

## VI. UML Class Diagram

![back](/resources/images/UML_Class_Diagram.png)


## VII . Testing Plan

- Testing is done with FX-Testing and JUnit for the unit tests.
- Care needs to be taken while doing the integration tests and the final product to make sure all of the features work concurrently without issues.
- All of the testing information is included in the testing document.

## VIII . Deployment Plan

The finished software is expected to be deployed in windows.
Any interactions with the application and windows will be managed by the framework.
Meaning in terms of retaining windows compatibility for maintenance would all depend on the framework.
Sticking to the same framework will inevitably result in compatibility issues in the future which would then mean a complete overhaul of the software, but this is currently not a cause for concern.

## IX . Maintenance Plan
This section covers our future plan for the software.
While the main requirements are implemented and tested, new features could also be added.
We felt like The user needs to be slightly more restricted for preview box transformations, this is because it's very easy to overlap or throw preview boxes out of bounds.
therefore,
- additional work should be done on the preview camera, as well as new methods to eliminate overlapping by alerting the user upon exporting.
- This in addition to implementing a snapping feature that magnetizes preview boxes on edge proximity.
- Additional file extensions could also be added.
- The preview window could be improved by using actual media to visualize the objects instead of rectangle boxes. Due to framework being rather difficult to use when working with ongoing processes, this might prove challenging.
- The preview could also playback the selected zone too.
- The software could also benefit from UI changes. thus more work could be done to improve it's UI.
