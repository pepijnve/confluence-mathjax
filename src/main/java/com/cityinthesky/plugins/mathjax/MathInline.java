package com.cityinthesky.plugins.mathjax;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.renderer.v2.components.HtmlEscaper;

import java.util.Map;

public class MathInline extends MathMacro {
  public MathInline() {
    super(OutputType.INLINE);
  }
}
