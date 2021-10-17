# Functional Specifications

## I . Overview(selin)

The main thing that is aimed with the software is to join multiple videos and play them simultaneously by using a timeline and layout. It will also be possible to place pictures within videos. With basic tools, the user will be able to create original designs by editing the videos and images. Videos can be trimmed, rotated while images can be filtered and adjusted. The final version of combined videos and pictures can be turned into different file formats.

## II . Product Perspective(Khizr)
A per the costumers requirements the software is a video creator that has the ability the stitch and synchronize multiple video files. It also should allow for a certain amount of audio editing and give the user the choice of which format they would like to export the new video file as.

A simple example would be 'Windows Movie Maker'.
![back](/resources/images/Windowsmm.jpg)
.
## III . Product Features
With deliberation over our approach and in congruence with the specified requirements, we decided on the overall system structure as the following ;
The Interface will mainly consist of two screens, one will show a preview of the current state, another will act as a timeline and a mini editor for the use of the following features :
- File importing. This will allow users to use media files without having the manually copy them to the application directory manually.
- ( Optionally ) Media files drag and drop features. This makes importing files to the timeline easier.
- Media exporting. Due to video file creation being complicated, it would be easier to simply display / play all the videos in a new window. this window will also include options to pause, resume and seek.
- The ability to manually plot your videos. This will also be accompanied with a preview screen.
- A selectable zone, this will allow the user to specify which part of the video to render / export.
- Allow video clips to move in the timeline; this allows the user to set synchronize the clips relative to eachother with a visual indicator. optionally the user could manually set a " seek " value. this could be used when more precition is required.
- ( optionally ) An options menu for individual clips, accessed by double-clicking on the clip. could also display information such as starting and ending times, framerate or allow the user to mute / manually seek.
- Allow the user to mute individual clips, can be found in the clip options menu.
- A support for images too. images could be dropped into the timeline and their start and end times could be set. other visual features also apply.
- ( Optionally ) A support for audio files. Audio features also apply.

## IV . Screen Plans
The general look of the software is still in deliberation. but as stated before, we have decided to use two windows for plotting / synching and one window for the final render.

Below is a quick screen plan of the timeline, it contains imported media as layers with different color codes as well as a picture identifier. The timeline also allows you to import media, see media details and render the result.

![back](/resources/images/timeline.png)

The timeline is also accompanied with a preview to visualize your plot. you're able to manually transform ( for now limited to translating and resizing ) videos and pictures.
Transform constraints will be discussed later on in system design.

![back](/resources/images/prev.png)

Once rendered, this window will appear. This window will act as a video player for all your imported media with your chosen plot.

![back](/resources/images/render.png)
## V. Software Requirements

### Java :
A fair amount of research was conducted to determine the base language once the product features were in place and the envisioned application did not seem complicated enough for different languages / frameworks to make a significant change in the creation proccess or the final product. Therefore we chose we chose a language we're all familiar and comfortable with, java.
### JavaFX :
JavaFX seems like the best choice out of Java's application frameworks mainly due to Swing and AWT being outdated and less flexible despite it's wide use.
### JUnit :
JUnit was chosen as the main testing framework due to it's familiarity and importance.
### Maven :
To make aquireing frameworks, project building and reporting easier, we will be using Maven.

## VI. Risk Analysis (nehal)


## VII . version control Requirements(selin)

The version control system enables all team members to work together on the same project. Since every change done by team members is stored in the database, team members can cooperate without any confusion. It is also necessary to check each team member's contribution to the project. For this project Git is being used as version control, so everyone can access the latest version.

## VIII. Testing Requirements(nehal)
// this proccess requires testing because :
