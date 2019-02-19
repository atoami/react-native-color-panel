//
//  RNColorPanel.m
//  RNColorPanel
//
//  Created by Gang on 2/19/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <React/RCTConvert.h>
#import "RNColorPanel.h"

@implementation RNColorPanel

- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        _colorPickerView = [[HRColorPickerView alloc] init];
        _colorPickerView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
        _colorPickerView.frame = frame;
        
        [self addSubview:_colorPickerView];
    }
    return self;
}

- (void)setFullColor:(BOOL)fullColor {
    _fullColor = fullColor;
    
    
    if (_fullColor) {
        HRColorMapView *colorMapView = [[HRColorMapView alloc] init];
        colorMapView.saturationUpperLimit = @1;
        colorMapView.tileSize = @1;
        [_colorPickerView addSubview:colorMapView];
        _colorPickerView.colorMapView = colorMapView;
    } else {
        HRColorMapView *colorMapView = [[HRColorMapView alloc] init];
        colorMapView.saturationUpperLimit = @0.9;
        colorMapView.tileSize = @16;
        [_colorPickerView addSubview:colorMapView];
        _colorPickerView.colorMapView = colorMapView;
    }
    
    [self setNeedsLayout];
}

- (void)setColor:(NSNumber *)colorValue {
    UIColor *color = [RCTConvert UIColor:colorValue];
    _colorPickerView.color = color;
    
    [self setNeedsLayout];
}

- (void)setBrightnessLowerLimit:(NSNumber *)brightnessLowerLimit {
    _colorPickerView.brightnessSlider.brightnessLowerLimit = brightnessLowerLimit;
    [self setNeedsLayout];
}

@end
