
#if __has_include("RCTViewManager.h")
#import "RCTViewManager.h"
#else
#import <React/RCTViewManager.h>
#endif

#import "RNColorPanel.h"
#import "HRColorUtils.h"

@interface RNColorPanelManager : RCTViewManager

@property (nonatomic, strong) RNColorPanel *colorPanel;

@end
  
