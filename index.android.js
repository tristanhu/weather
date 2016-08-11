'use strict';

import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  TouchableHighlight,
  View,
  NativeModules,
  DeviceEventEmitter 
} from 'react-native';

var MyToastModule = NativeModules.MyToastModule;

var HelloWorld = React.createClass({
   getInitialState() {
    return {conte:'back'};
  },

  componentWillMount: function() {
    DeviceEventEmitter.addListener('event_menu_down', function(e: Event) {
      MyToastModule.abc('event_menu_down', 
        MyToastModule.SHORT
        );
    });
  },

  render() {
    var te = this.state.conte;
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>{te}</Text>
        <TouchableHighlight onPress={this.toas}>
          <Text style={styles.hello}>Press</Text>
        </TouchableHighlight>
      </View>
    )
  },

  toas(){
    MyToastModule.show('Awesome', 
      MyToastModule.SHORT,
      (msg)=>{this.setState({conte:"error " + msg})},
      (msg)=>{this.setState({conte:"success " + msg})},
      );
 
    // MyToastModule.abc('Awesome', 
    //   MyToastModule.SHORT
    //   );
  }
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    flexDirection:'row',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent('HelloWorld', () => HelloWorld);