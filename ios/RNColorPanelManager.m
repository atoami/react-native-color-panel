
#import "RNColorPanelManager.h"

@implementation RNColorPanelManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
    _colorPanel = [[RNColorPanel alloc] init];
    
    [_colorPanel.colorPickerView addTarget:self action:@selector(didChangeColor:) forControlEvents:UIControlEventValueChanged];

    return _colorPanel;
}

- (void)didChangeColor:(HRColorPickerView*)pickerView {
    if (_colorPanel.onColorChange) {
        _colorPanel.onColorChange(@{@"color": [HRColorUtils hexStringFromColor:pickerView.color]});
    }
}

RCT_EXPORT_VIEW_PROPERTY(fullColor, BOOL)
RCT_EXPORT_VIEW_PROPERTY(color, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(brightnessLowerLimit, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(onColorChange, RCTBubblingEventBlock)

@end
