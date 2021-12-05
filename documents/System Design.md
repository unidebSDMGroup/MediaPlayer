# System Design

## I . Core Objectives.

At the very basic functional level the product should deliver the following core functionalities.

- Working application in a windows enviroment.
- Import multiple videos/images/audio from files with preset file types.
- Allow editing of size,timeline location of imported media elements.
- Export the combined video into a video file.


##II . Theoretical Development Strategy.

- SCRUM model for development cycles.
- Project Building using Maven.
- Media Application using JavaFX .
- Testing JUnit and FX-Testing.

//should incluide a project timeline


## III . Theoretical Feature List.

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

### 3 . Other Funcitonal Features.

- To be added.

## IV. Technical Development Strategy.

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
		- Audio is not hidable.(hide attribute not inherited)
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

## V. UML Class Diagram

![back](/resources/images/UML_Class_Diagram.png)


## VIII . Testing Plan



## IX . Deployment Plan

## X . Maintenance Plan
