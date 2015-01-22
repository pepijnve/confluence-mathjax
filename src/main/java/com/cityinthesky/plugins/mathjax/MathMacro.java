package com.cityinthesky.plugins.mathjax;

import java.util.Map;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.renderer.v2.components.HtmlEscaper;

public class MathMacro implements Macro {
  private final OutputType outputType;
  private final String template;

  public MathMacro(OutputType outputType) {
    this.outputType = outputType;
    this.template = "templates/math.vm";
  }

  @Override
  public BodyType getBodyType() {
    return BodyType.PLAIN_TEXT;
  }

  @Override
  public OutputType getOutputType() {
    return outputType;
  }

  @Override
  public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext)
      throws MacroExecutionException {
    Map<String, Object> context = MacroUtils.defaultVelocityContext();

    String syntax = parameters.get("syntax");
    if ("latex".equalsIgnoreCase(syntax)) {
      // LaTeX
      if (bodyContent.length() == 0) {
        bodyContent = "\\int_{-\\infty}^\\infty \\mbox{e}^{-x^2} \\mbox{d}x = \\sqrt{\\pi}";
      }

      if (outputType == OutputType.INLINE) {
        context.put("start", "\\( ");
        context.put("end", " \\)");
      } else {
        context.put("start", "\\[ ");
        context.put("end", " \\]");
      }
    } else {
      // AsciiMath
      if (bodyContent.length() == 0) {
        bodyContent = "sum_(i=1)^n i^3=((n(n+1))/2)^2";
      }

      context.put("start", "`");
      context.put("end", "`");
    }

    context.put("body", HtmlEscaper.escapeAll(bodyContent, true));

    return VelocityUtils.getRenderedTemplate(template, context);
  }
}
