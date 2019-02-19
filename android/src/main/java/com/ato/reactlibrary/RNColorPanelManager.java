
package com.ato.reactlibrary;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class RNColorPanelManager extends SimpleViewManager<RNColorPanel> {

  public static final String REACT_CLASS = "RNColorPanel";
  public static final String PROP_COLOR = "color";

  @Override
  public RNColorPanel createViewInstance(ThemedReactContext context) {
    return new RNColorPanel(context);
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @ReactProp(name = PROP_COLOR)
  public void setColor(RNColorPanel view, @Nullable int color) {
//    view.setColor(color);
  }

  @Nullable
  @Override
  public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
    return MapBuilder.<String, Object>builder()
            .put(
                    "colorDidChange",
                    MapBuilder.of(
                            "phasedRegistrationNames",
                            MapBuilder.of("bubbled", "onColorChange")))
            .build();
  }
}