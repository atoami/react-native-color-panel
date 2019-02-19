/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 * @lint-ignore-every XPLATJSCOPYRIGHT1
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, Dimensions} from 'react-native';
import RNColorPanel from 'react-native-color-panel';

const { width, height } = Dimensions.get('window');

export default class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      selectedColor: 'yellow',
      fullColor: true
    };
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={[styles.welcome, { color: this.state.selectedColor }]}>
          Welcome to React Native!
        </Text>
        <RNColorPanel
          style={styles.panel}
          fullColor={this.state.fullColor}
          color={this.state.selectedColor}
          brightnessLowerLimit={0}
          onColorChange={color => this.setState({ selectedColor: color })}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    marginTop: 50,
  },
  panel: {
    width: width - 80,
    height: (width - 80) * 1.0,
    margin: 20,
  }
});
