'use strict';


import React from 'react';
import { PropTypes } from 'react';

import { requireNativeComponent, View } from 'react-native';

var iface = {
	name : "MyReactTextView",
	propTypes : {
		src:PropTypes.string,
		borderRadius: PropTypes.number,
		resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
		nativeOnly:{onChange: true},
		onChangeMessage: React.PropTypes.func,
		...View.propTypes
	},
};

class MyReactTextView extends React.Component {
  constructor(props) {
    super(props);
    this._onChange = this._onChange.bind(this);
  }
  _onChange(event: Event) {
    if (!this.props.onChangeMessage) {
      return;
    }
    this.props.onChangeMessage(event.nativeEvent);
  }
  render() {
    return <RCTMyReactTextView {...this.props} onChange={this._onChange} />;
  }
}
MyReactTextView.propTypes = {
  /**
   * Callback that is called continuously when the user is dragging the map.
   */
 		onChangeMessage: React.PropTypes.func,
		src:PropTypes.string,
		borderRadius: PropTypes.number,
		resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
		nativeOnly:{onChange: true},
		...View.propTypes
};


var RCTMyReactTextView  = requireNativeComponent("MyReactTextView", MyReactTextView, {
    nativeOnly: {onChange: true}
});
module.exports = MyReactTextView;