package buildcraft.transport.client.render;

import net.minecraft.client.renderer.VertexBuffer;

import buildcraft.api.transport.pluggable.IPlugDynamicRenderer;

import buildcraft.lib.client.model.AdvModelCache;
import buildcraft.lib.client.model.MutableQuad;
import buildcraft.transport.BCTransportModels;
import buildcraft.transport.plug.PluggableGate;

public enum PlugGateRenderer implements IPlugDynamicRenderer<PluggableGate> {
    INSTANCE;

    private static final AdvModelCache cache = new AdvModelCache(BCTransportModels.GATE_DYNAMIC, PluggableGate.MODEL_VAR_INFO);

    public static void onModelBake() {
        cache.reset();
    }

    @Override
    public void render(PluggableGate gate, double x, double y, double z, float partialTicks, VertexBuffer vb) {
        vb.setTranslation(x, y, z);
        gate.setClientModelVariables();
        if (gate.clientModelData.hasNoNodes()) {
            gate.clientModelData.setNodes(BCTransportModels.GATE_DYNAMIC.createTickableNodes());
        }
        gate.clientModelData.refresh();
        MutableQuad copy = new MutableQuad();
        for (MutableQuad q : cache.getCutoutQuads()) {
            copy.copyFrom(q);
            copy.multShade();
            copy.render(vb);
        }
        vb.setTranslation(0, 0, 0);
    }
}
