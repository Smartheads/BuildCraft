package buildcraft.lib.client.guide.parts;

import java.util.List;

import com.google.common.collect.ImmutableList;

import buildcraft.lib.client.guide.GuiGuide;
import buildcraft.lib.client.guide.font.IFontRenderer;
import buildcraft.lib.client.resource.MarkdownResourceHolder;

public class GuidePage extends GuidePageBase {
    public final MarkdownResourceHolder creator;
    public final ImmutableList<GuidePart> parts;

    public GuidePage(GuiGuide gui, List<GuidePart> parts, MarkdownResourceHolder creator) {
        super(gui);
        this.creator = creator;
        this.parts = ImmutableList.copyOf(parts);
    }

    @Override
    public void setFontRenderer(IFontRenderer fontRenderer) {
        super.setFontRenderer(fontRenderer);
        for (GuidePart part : parts) {
            part.setFontRenderer(fontRenderer);
        }
    }

    @Override
    protected void renderPage(int x, int y, int width, int height, int index) {
        super.renderPage(x, y, width, height, index);
        PagePart part = new PagePart(0, 0);
        for (GuidePart guidePart : parts) {
            part = guidePart.renderIntoArea(x, y, width, height, part, index);
            if (numPages != -1 && part.page > index) {
                break;
            }
        }
        if (numPages == -1) {
            numPages = part.newPage().page;
        }
    }

    @Override
    public void handleMouseClick(int x, int y, int width, int height, int mouseX, int mouseY, int mouseButton, int index, boolean isEditing) {
        // TODO Auto-generated method stub
        super.handleMouseClick(x, y, width, height, mouseX, mouseY, mouseButton, index, isEditing);
    }
}
