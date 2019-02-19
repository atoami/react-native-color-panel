import React from 'react';
import { requireNativeComponent, processColor } from 'react-native';
import PropTypes from 'prop-types';

class ColorPanel extends React.Component {

  _onColorChange = (event) => {
    if (!this.props.onColorChange) {
      return;
    }

    this.props.onColorChange(event.nativeEvent.color);
  }

  render() {
    return (
      <RNColorPanel
        {...this.props}
        color={processColor(this.props.color)}
        onColorChange={this._onColorChange}
      />
    );
  }
}

ColorPanel.propTypes = {
  fullColor: PropTypes.bool,
  color: PropTypes.string,
  brightnessLowerLimit: PropTypes.number,
  onColorChange: PropTypes.func,
};

ColorPanel.defaultProps = {
  fullColor: true,
  color: 'rgba(255, 0, 0, 1)',
  brightnessLowerLimit: 0,
  onColorChange: undefined
};

const RNColorPanel = requireNativeComponent('RNColorPanel', ColorPanel);

export default ColorPanel;
