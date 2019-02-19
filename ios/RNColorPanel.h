//
//  RNColorPanel.h
//  RNColorPanel
//
//  Created by Gang on 2/19/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <React/RCTComponent.h>
#import "HRColorPicker.h"

@interface RNColorPanel : UIView

@property (nonatomic, strong) HRColorPickerView *colorPickerView;

@property (nonatomic) NSNumber *color;
@property (nonatomic) BOOL fullColor;
@property (nonatomic) NSNumber *brightnessLowerLimit;
@property (nonatomic, copy) RCTBubblingEventBlock onColorChange;

@end
