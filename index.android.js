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

var MyReactTextView = require('./MyReactTextView');

var HelloWorld = React.createClass({
   getInitialState() {
    return {conte:'back'};
  },

  componentWillMount: function() {
    DeviceEventEmitter.addListener('key_event_down', function(e: Event) {
      MyToastModule.showABC('key_event_down', 
        MyToastModule.SHORT
        );
    });
  },

  render() {
    var te = this.state.conte;
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>{te}</Text>
        <MyReactTextView 
            style={styles.myText}
            src={[{abc:"ttt", ddd:"aaa"}, {abc:'ddd'}]} 
            resizeMode="contain"
            onChangeMessage={(msg)=>MyToastModule.showABC(JSON.stringify(msg),MyToastModule.SHORT)}>

            </MyReactTextView>

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
  myText:{
    backgroundColor:'blue',
    width:100,
    height:100,
  }
});

AppRegistry.registerComponent('HelloWorld', () => HelloWorld);