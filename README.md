
# react-native-color-panel

## Getting started

`$ npm install react-native-color-panel --save`

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
import RNColorPanel from 'react-native-color-panel';

// TODO: What to do with the module?
RNColorPanel;
```
