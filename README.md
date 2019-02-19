
# react-native-color-panel

<div>
<img width="200" height="350" style="float: left" src="https://user-images.githubusercontent.com/17483938/53002929-c8def500-3425-11e9-9b5f-02948b3aad98.gif" />
<img width="200" height="350" style="float: left" src="https://user-images.githubusercontent.com/17483938/53003144-3559f400-3426-11e9-8212-6a7cd27d42f0.PNG" />
<img width="200" height="350" style="float: left" src="https://user-images.githubusercontent.com/17483938/53003146-3559f400-3426-11e9-9752-5c0c73afa92e.PNG" />
</div>

## Getting started

`$ npm install react-native-color-panel --save`

or

`$ yarn add react-native-color-panel`

### Mostly automatic installation

`$ react-native link react-native-color-panel`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-color-panel` and add `RNColorPanel.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNColorPanel.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.ato.reactlibrary.RNColorPanelPackage;` to the imports at the top of the file
  - Add `new RNColorPanelPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-color-panel'
  	project(':react-native-color-panel').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-color-panel/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-color-panel')
  	```

## Usage
```javascript
import ColorPanel from 'react-native-color-panel';

<ColorPanel
  style={{ flex: 1 }}
  fullColor={true}
  color={this.state.selectedColor}
  brightnessLowerLimit={0}
  onColorChange={color => this.setState({ selectedColor: color })}
/>
```
